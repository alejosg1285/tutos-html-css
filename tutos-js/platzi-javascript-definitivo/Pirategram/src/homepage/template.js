var yo = require('yo-yo');
var layout = require('../layout');
var picture = require('../picture-card');
var translate = require('../translate').message;
var request = require('superagent');

module.exports = function (pictures) {
    var el = yo `<div class="container timeline">
        <!-- Modal Structure -->
        <div id="modalCamara" class="modal center-align">
            <div class="modal-content">
                <div id="camara-input" class="camara-picture"></div>
                <div id="picture-preview" class="camara-picture hide"></div>
            </div>
            <div class="modal-footer">
                <button id="shoot" class="waves-effect waves-light btn-flat">
                    <i class="fa fa-camera-retro" aria-hidden="true"></i>
                </button>
                <button id="uploadPicture" class="waves-effect waves-light cyan btn-flat hide">
                    <i class="fa fa-upload" aria-hidden="true"></i>
                </button>
                <button id="cancelPicture" class="waves-effect waves-light red btn-flat hide">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
            </div>
        </div>

        <div class="row">
            <div class="col s12 m10 offset-m1 l8 offset-l2 center-align">
                <form id="formUpload" enctype="multipart/form-data" class="form-upload" onsubmit=${subirImagen}>
                    <a class="waves-effect waves-light btn modal-trigger" href="#modalCamara">
                        <i class="fa fa-camera-retro" aria-hidden="true"></i>
                    </a>
                    <div id="fileName" class="fileUpload btn btn-flat cyan">
                        <span><i class="fa fa-upload" aria-hidden="true"></i> ${translate('upload-picture')}</span>
                        <input type="file" name="picture" id="file" class="upload" onchange=${changeHide} />
                    </div>
                    <button id="btnUpload" type="submit" class="btn btn-flat cyan hide">${translate('upload')}</button>
                    <button id="btnCancel" type="button" class="btn btn-flat red hide" onclick=${cancel}><i class="fa fa-times" aria-hidden="true"></i></button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col s12 m10 offset-m1 l6 offset-l3" id="picture-cards">
                ${pictures.map(function(pic) {
                    return picture(pic);
                })}
            </div>
        </div>
    </div>`;

    function toggleHide() {
        document.getElementById('fileName').classList.toggle('hide');
        document.getElementById('btnUpload').classList.toggle('hide');
        document.getElementById('btnCancel').classList.toggle('hide');
    }

    function changeHide() {
        toggleHide();
    }

    function cancel() {
        toggleHide();
        document.getElementById('formUpload').reset();
    }

    function subirImagen(ev) {
        ev.preventDefault();

        var data = new FormData(this);

        request
            .post('/api/pictures')
            .send(data)
            .end(function (err, res) {
                console.log(err, res);
            })
    }

    return layout(el);
}
