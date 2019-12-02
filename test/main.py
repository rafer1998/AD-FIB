import flask
from flask import render_template, url_for, flash, redirect

from forms import RegistrationForm, LoginForm


app = flask.Flask(__name__)

app.config['SECRET_KEY'] = '5791628bb0b13ce0c676dfde280ba245'

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
    form = RegistrationForm()
    if form.validate_on_submit():
        flash(f'Has creado la cuenta para {form.username.data}!', 'success')
        return redirect(url_for('home'))

    return render_template('register.html', title='Register', form=form)

@app.route('/login', methods=['GET', 'POST'])
def login():
    form = LoginForm()
    if form.validate_on_submit():
        if form.email.data == 'admin@admin.com' and form.password.data == 'admin':
            flash(f'Has iniciado sesion correctamente!', 'success')
            return redirect(url_for('home'))
        else:
            flash(f'Error al iniciar sesion!', 'danger')

    return render_template('login.html', title='Login', form=form)

app.run(host="0.0.0.0", port=5000, debug=True)

