'use strict'

const uuid = require('uuid-base62')

const fixtures = {
  getImage () {
    let id = uuid.uuid()
    return {
      description: 'espero #algun dia terminar #videotuto #node',
      tags: [ 'algun', 'videotuto', 'node' ],
      url: `url-false.php/${uuid.base64()}.jpg`,
      likes: 0,
      liked: false,
      userId: uuid.uuid(),
      publicId: uuid.encode(id),
      id: id,
      createdAt: new Date().toString()
    }
  },
  getImages (n) {
    let images = []
    while (n-- > 0) {
      images.push(this.getImage())
    }
    return images
  },
  getUser () {
  return {
    id: uuid.uuid(),
    name: 'Superman',
    username: 'Kal-El',
    email: 'superman@krypton.com',
    password: 'KalEl',
    createdAt: new Date().toString()
  }
  }
}

module.exports = fixtures
