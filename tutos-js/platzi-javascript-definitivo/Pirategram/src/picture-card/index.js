var yo = require('yo-yo');
var moment = require('moment');
var translate = require('../translate');

module.exports = function pictureCard(picture) {
    var ele;

    function render(pic) {
        return yo `<div class="card ${pic.liked ? 'liked' : ''}">
            <div class="card-image waves-effect waves-block waves-light">
                <img class="activator" src="${pic.url}" ondblclick=${like.bind(null, null, true)} />
                <i class="fa fa-heart like-heart ${pic.likeHeart ? 'liked' : ''}" aria-hidden="true"></i>
            </div>
            <div class="card-content">
              <a href="/${pic.user.username}" class="card-title grey-text text-darken-4">
                    <img src="${pic.user.avatar}" class="avatar" />
                    <span class="username">${pic.user.username}</span>
                </a>
                
                <p>
                    <a href="#" class="left" onclick=${like.bind(null, true)}><i class="fa fa-heart-o" aria-hidden="true"></i></a>
                    <a href="#" class="left" onclick=${like.bind(null, false)}><i class="fa fa-heart" aria-hidden="true"></i></a>
                    <span class="left likes">${translate.message('likes', {numPhotos: pic.likes})}</span>
                </p>
            </div>
          </div>`;
    }
    //esto en el espacio de arriba, antes de p (<small class="right time">${translate.date.format(pic.createdAt)}</small>)
    function like(liked, dblclick) {
        if (dblclick) {
            picture.likeHeart = picture.liked = !picture.liked;
            liked = picture.liked;
        } else {
            picture.liked = liked;
        }
        //picture.liked = liked;
        picture.likes += liked ? 1 : -1;

        function doRender() {
            var newEle = render(picture);
            yo.update(ele, newEle);
        };

        doRender();

        setTimeout(function () {
            picture.likeHeart = false;
            doRender();
        }, 1500);

        return false;
    }

    ele = render(picture);

    return ele;
}
