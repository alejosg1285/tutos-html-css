import page from 'page'
import header from '../header'
import title from 'title'
import empty from 'empty-element'
import template from './template'

page('/:username', header, loadUser, function (context, next) {
    var main = document.getElementById('main-container');
    title(`Piracte - ${context.params.username}`);
    empty(main).appendChild(template(context.user));
});

page('/:username/:id', header, loadUser, function (context, next) {
    var main = document.getElementById('main-container');
    title(`Piracte - ${context.params.username}`);
    empty(main).appendChild(template(context.user));

    $(`#modal${context.params.id}`).modal('open');

    $('.modal').modal({
        complete: function () {
            page(`/${context.params.username}`)
        } // Callback for Modal close
    });

});

async function loadUser(ctx, next) {
    try {
        ctx.user = await fetch(`/api/user/${ctx.params.username}`).then(res => res.json());
        next();
    } catch (err) {
        console.log(err);
    }
}
