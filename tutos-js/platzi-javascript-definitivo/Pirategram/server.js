var express = require('express');
var multer = require('multer');
var ext = require('file-extension');

var storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, './uploads');
    },
    filename: function (req, file, cb) {
        cb(null, file.fieldname + '-' + Date.now() + '.' + ext(file.originalname));
    }
});

var upload = multer({
    storage: storage
}).single('picture');

var app = express();

app.set('view engine', 'pug');

app.use(express.static('public'));

app.get('/', function (req, res) {
    res.render('index', {
        title: 'Pirategram'
    });
});
app.get('/signup', function (req, res) {
    res.render('index', {
        title: 'Piratearas'
    });
});
app.get('/signin', function (req, res) {
    res.render('index', {
        title: 'Pirateando'
    });
});

app.get('/api/pictures', function (req, res) {
    var pictures = [
        {
            user: {
                username: "Superman",
                avatar: "images/superman.jpg"
            },
            url: "images/superman.jpg",
            likes: 12535,
            liked: true,
            createdAt: new Date().getTime()
        },
        {
            user: {
                username: "Superman",
                avatar: "images/superman.jpg"
            },
            url: "images/superman.jpg",
            likes: 12535,
            liked: false,
            createdAt: new Date().setDate(new Date().getDate() - 10)
        }
    ];

    ///setTimeout(function () {
    //res.send(pictures);
    //}, 2000);
    setTimeout(() => res.send(pictures), 2000);
});

app.post('/api/pictures', function (req, res) {
    upload(req, res, function (err) {
        if (err) return res.send(500, 'Error uploading picture');

        res.send('File uploaded');
    });
});

app.get('/api/user/:username', function (req, res) {
    const user = {
        username: req.params.username,
        avatar: 'images/superman.jpg',
        pictures: [
            {
                id: 1,
                src: 'images/Kingdom-Come-MANS.jpg',
                likes: 6
            },
            {
                id: 2,
                src: 'images/Supermans_classic_pose.png',
                likes: 16
            },
            {
                id: 3,
                src: 'images/david-ayer-keen-to-do-a-superman-film.jpg',
                likes: 66
            }
        ]
    }

    res.send(user);
});

app.get('/:username', function (req, res) {
    /*res.send('hola');*/
    res.render('index', {
        title: 'Usuario ' + req.params.user
        //title: 'Usuario '
    });
});

app.get('/:username/:id', function (req, res) {
    res.render('index', {
        title: 'Usuario ' + req.params.username
    });
});

app.listen(3000, function (err) {
    if (err) return console.log('Se acabo esto!'), process.exit(1);

    console.log('Por ahora todo bien en el 3000');
});
