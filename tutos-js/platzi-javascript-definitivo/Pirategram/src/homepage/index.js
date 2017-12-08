var page = require('page');
var empty = require('empty-element');
var template = require('./template');
var title = require('title');
var request = require('superagent');
var header = require('../header');
var axios = require('axios');
import webcam from 'webcamjs';
import picture from '../picture-card';

page('/', header, loading, loadPictures, function (context, next) {
    title('Pirategram');
    var main = document.getElementById('main-container');

    empty(main).appendChild(template(context.pictures));

    const camaraInput = $('#camara-input');
    const picturePreview = $('#picture-preview');
    const shootButton = $('#shoot');
    const uploadButton = $('#uploadPicture');
    const cancelButton = $('#cancelPicture');

    function reset() {
        camaraInput.removeClass('hide');
        picturePreview.addClass('hide');
        shootButton.removeClass('hide');
        uploadButton.addClass('hide');
        cancelButton.addClass('hide');
    }

    cancelButton.click(reset);

    $('#modalCamara').modal({
        ready: function () {
            webcam.attach('#camara-input');
            shootButton.click((ev) => {
                webcam.snap((data_uri) => {
                    picturePreview.html('<img src="' + data_uri + '" />');

                    camaraInput.addClass('hide');
                    picturePreview.removeClass('hide');
                    shootButton.addClass('hide');
                    uploadButton.removeClass('hide');
                    cancelButton.removeClass('hide');

                    uploadButton.off('click');
                    uploadButton.click(() => {
                        const pic = {
                            user: {
                                username: "Superman",
                                avatar: "images/superman.jpg"
                            },
                            url: data_uri,
                            likes: 0,
                            liked: false,
                            createdAt: +new Date()
                        }

                        $('#picture-cards').prepend(picture(pic));
                        reset();
                        $('#modalCamara').modal('close');
                    });
                });
            });
        },
        complete: function () {
            webcam.reset();
            reset();
        }
    });
});

function loading(ctx, next) {
    var el = document.createElement('div');
    el.classList.add('spinner');
    document.getElementById('main-container').appendChild(el);
    next();
}

/*function loadPictures(ctx, next) {
    request
        .get('/api/pictures')
        .end(function (err, res) {
            if (err) return console.log(err);

            ctx.pictures = res.body;
            next();
        });
}*/
/*function loadPictures(ctx, next) {
    axios
        .get('/api/pictures')
        .then(function (res) {
            ctx.pictures = res.data;
            next();
        })
        .catch(function (err) {
            console.log(err);
        });
}*/
/*function loadPictures(ctx, next) {
    fetch('/api/pictures')
        .then(function (res) {
            return res.json();
        })
        .then(function (pictures) {
            ctx.pictures = pictures;
            next();
        })
        .catch(function (err) {
            console.log(err);
        });
}*/
async function loadPictures(context, next) {
    try {
        context.pictures = await fetch('/api/pictures').then(res => res.json());
        next();
    } catch (err) {
        return console.log(err);
    }
}
