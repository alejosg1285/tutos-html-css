import yo from 'yo-yo'
import layout from '../layout'
var translate = require('../translate').message;

export default function userPageTemplate(user) {
    var el = yo `<div class="container">
            <section class="row">
                <div class="col s12 m4">
                    <figure id="pic-profile">
                        <img src="${user.avatar}" class="responsive-img circle"/>
                    </figure>
                </div>
                <div class="col s12 m8">
                    <div class="title-profile">
                        <h4>${user.username}</h4>
                        <a href="#" class="waves-effect waves-light btn">Le sigues</a>
                        <span><i class="fa fa-ellipsis-h" aria-hidden="true"></i></span>
                    </div>
                    <p class="flow-text desc-profile">
                        <strong>Superman</strong> The most recognized superhero in pop culture, Superman has been elevated to mythic folkhero status.
                    </p>
                    <div class="facts-profile">
                        <label>
                            <strong>1938</strong> Appearance
                        </label>
                        <label>
                            <strong>995</strong> Comicbooks
                        </label>
                        <label>
                            <strong>6</strong> Movies
                        </label>
                    </div>
                </div>
            </section>
            <section class="row">
                ${user.pictures.map(function(picture){
                    return yo`<div class="col s12 m4">
                            <a href="/${user.username}/${picture.id}" class="picture-container">
                                <figure id="pic-profile">
                                    <img src="${picture.src}" class = "picture responsive-img" />
                                </figure>
                                <div class="likes"><i class="fa fa-heart-o" aria-hidden="true"></i> ${picture.likes}</div>
                            </a>
                            <div id="modal${picture.id}" class="modal modal-fixed-footer">
                                <div class="modal-content">
                                    <figure id="pic-profile">
                                        <img src="${picture.src}" class = "responsive-img" />
                                    </figure>
                                </div>
                                <div class="modal-footer">
                                  <div class="btn btn-flat likes"><i class="fa fa-heart-o" aria-hidden="true"></i> ${translate('likes', {numPhotos: picture.likes})}</div>
                                </div>
                            </div>
                        </div>`;
                })}
            </section>
        </div>`;

    return layout(el);
}
