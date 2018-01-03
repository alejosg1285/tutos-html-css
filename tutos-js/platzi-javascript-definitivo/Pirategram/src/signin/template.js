var yo = require('yo-yo');
var landing = require('../landing');
var translate = require('../translate');

var signinForm = yo `<div class="col s12 m7">
                            <div class="row">
                                <div class="signup-box">
                                    <h1 class="platzigram">Platigram</h1>
                                    <form action="" class="signup-form">
                                        <h2>Logueate para lo que sea</h2>
                                        <div class="section">
                                            <a href="" class="btn btn-fb hide-on-small-only">${translate.message('signup.facebook')}</a>
                                            <a href="" class="btn btn-fb hide-on-med-and-up">
                                                <i class="fa fa-facebook-official" aria-hidden="true"></i> ${translate.message('signup.facebook.movil')}
                                            </a>
                                        </div>
                                        <div class="divider"></div>
                                        <div class="section">
                                            <input type="text" id="login" name="login" placeholder="${translate.message('username.placeholder')}">
                                            <input type="password" id="password" name="password" placeholder="${translate.message('password.placeholder')}">
                                            <button class="btn waves-effect waves-ligth btn-signup" type="submit">${translate.message('signin')}</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="row">
                                <div class="login-box">
                                    ${translate.message('signin.not-have-account')}
                                    <a href="/signup">${translate.message('signup.call-to-action')}</a>
                                </div>
                            </div>
                        </div>`;

module.exports = landing(signinForm);
