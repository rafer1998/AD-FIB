from datetime import date

from clickbuy.models import User, Products
from flask import render_template, url_for, flash, redirect, request
from clickbuy import app, db, bcrypt
from clickbuy.forms import RegistrationForm, LoginForm, UpdateAccountForm, SearchForm, AddProductForm
from flask_login import login_user, current_user, logout_user, login_required
import secrets
import os

products = [
    {
        'author' : 'Ruben Barcelo',
        'product' : 'Coche',
        'units' : '2',
        'date_posted' : 'April 20, 2018'
    },
{
        'author' : 'Carlos Jimenez',
        'product' : 'Moto',
        'units' : '1',
        'date_posted' : 'May 25, 2018'
    }
]


@app.route('/')
@app.route('/home')
def home():
    return render_template('home.html', products=products)


@app.route('/register', methods=['GET', 'POST'])
def register():
    # Crear un nuevo usuario
    if current_user.is_authenticated:
            #Si ya estamos registrados -> reedirigir a home
            return redirect(url_for('home'))

    form = RegistrationForm()
    if form.validate_on_submit():
        #crear un nuevo usuario en la BD
        hashed_password = bcrypt.generate_password_hash(form.password.data).decode('utf-8')
        user = User(username=form.username.data, email=form.email.data, password=hashed_password)
        db.session.add(user)
        db.session.commit()

        flash(f'Se ha creado correctamente creado la cuenta!. Ya puedes iniciar sesion.', 'success')
        return redirect(url_for('login'))

    return render_template('register.html', title='Register', form=form)

@app.route('/addproduct', methods=['GET', 'POST'])
def addproduct():
    # Anadir un nuevo producto
    form = AddProductForm()
    if form.validate_on_submit():
        products = Products(nameArt=form.nameArt.data, units=form.units.data, user_id=current_user.id)
        db.session.add(products)
        db.session.commit()

        flash(f'Se ha creado correctamente creado el producto!.', 'success')
        return redirect(url_for('home'))

    return render_template('addproduct.html', title='Afegir un producte', form=form)


@app.route('/login', methods=['GET', 'POST'])
def login():
    # Iniciar sesion
    if current_user.is_authenticated:
            #Si ya estamos registrados -> reedirigir a home
            return redirect(url_for('home'))

    form = LoginForm()
    if form.validate_on_submit():
        user = User.query.filter_by(email=form.email.data).first()
        if user and bcrypt.check_password_hash(user.password, form.password.data):
            login_user(user,remember=False)
            return redirect(url_for('home'))
        else:
            flash(f'Inicio de sesion incorrecto!', 'danger')
    return render_template('login.html', title='Login', form=form)


@app.route('/logout')
def logout():
    # Cerrar sesion del usuario actual
    logout_user()
    return redirect(url_for('home'))


def save_picture(form_picture):
    # Guardar imagen del usuario
    random_hex = secrets.token_hex(8)
    _, f_ext = os.path.splitext(form_picture.filename)
    picture_fn = random_hex + f_ext
    picture_path = os.path.join(app.root_path, 'static/profile_pics', picture_fn)
    form_picture.save(picture_path)

    return picture_fn


@app.route('/account', methods=['GET', 'POST'])
@login_required
def account():
    # Cuenta del usuario
    form = UpdateAccountForm()
    if form.validate_on_submit():
        # Actualizar informacion del usuario
        if form.picture.data:
            # Si va a actualizar la imagen del usuario
            picture_file = save_picture(form.picture.data)
            current_user.image_file = picture_file

        current_user.username = form.username.data
        current_user.email = form.email.data
        db.session.commit()
        flash(f'Se ha actualizado la informacion de tu cuenta!', 'success')
        return redirect(url_for('account'))
    elif request.method == 'GET':
        # Mostrar la informacion del usuario
        form.username.data = current_user.username
        form.email.data = current_user.email

    image_file = url_for('static', filename='profile_pics/' + current_user.image_file)
    return render_template('account.html', title='Account', image_file=image_file, form=form)


@app.route('/search', methods=['GET', 'POST'])
def search():
    # Buscar un producto
    form = SearchForm()
    if form.validate_on_submit():
        return redirect(url_for('resultSearch', nameProduct=form.productName.data))

    return render_template('search.html', title='Search', form=form)


@app.route('/resultSearch/<string:nameProduct>')
def resultSearch(nameProduct):
    # Mostrar los productos encontrados
    return render_template('resultSearch.html', products=products, nameProduct=nameProduct)

