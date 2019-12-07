from flask_wtf import FlaskForm
from flask_wtf.file import FileField, FileAllowed
from wtforms import StringField, PasswordField, SubmitField
from wtforms.validators import DataRequired, EqualTo, Email, ValidationError
from clickbuy.models import User
from flask_login import current_user

class RegistrationForm(FlaskForm):
    username = StringField('Nombre Usuario', validators=[DataRequired()])
    email = StringField('Email', validators=[DataRequired(), Email()])
    password = PasswordField('Contraseña', validators=[DataRequired()])
    confirm_password = PasswordField('Confirmar Contraseña', validators=[DataRequired(), EqualTo('password')])
    submit = SubmitField('Registrarse')

    def validate_username(self, username):
        user = User.query.filter_by(username=username.data).first()
        if user:
           # Si el usuario existe -> error
           raise ValidationError('El usuario ya esta escogido. Escribe uno diferente.')
    def validate_email(self, email):
        user = User.query.filter_by(email=email.data).first()
        if user:
            # Si el usuario existe -> error
            raise ValidationError('El email ya esta escogido. Escribe uno diferente.')


class LoginForm(FlaskForm):
    email = StringField('Email', validators=[DataRequired(), Email()])
    password = PasswordField('Contraseña', validators=[DataRequired()])
    submit = SubmitField('Iniciar Sesion')


class UpdateAccountForm(FlaskForm):
    username = StringField('Nombre Usuario', validators=[DataRequired()])
    email = StringField('Email', validators=[DataRequired(), Email()])
    picture = FileField('Icono Usuario', validators=[FileAllowed(['jpg', 'png'])])
    submit = SubmitField('Actualizar')

    def validate_username(self, username):
        if username.data != current_user.username:
            # Unicamente si es diferente al actual
            user = User.query.filter_by(username=username.data).first()
            if user:
               # Si el usuario existe -> error
               raise ValidationError('El usuario ya esta escogido. Escribe uno diferente.')

    def validate_email(self, email):
        if email.data != current_user.email:
            # Unicamente si es diferente al actual
            user = User.query.filter_by(email=email.data).first()
            if user:
                # Si el usuario existe -> error
                raise ValidationError('El email ya esta escogido. Escribe uno diferente.')


class SearchForm(FlaskForm):
    productName = StringField('Nombre Producto', validators=[DataRequired()])
    submit = SubmitField('Buscar')

class AddProductForm(FlaskForm):
    nameArt = StringField('Nom del article', validators=[DataRequired()])
    units = StringField('Unitats que poso en venda', validators=[DataRequired()])
    submit = SubmitField('Añadir Producto')

