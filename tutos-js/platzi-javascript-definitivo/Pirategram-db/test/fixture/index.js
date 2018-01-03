'use strict'

const uuid = require('uuid-base62')

const fixtures = {
  getImage () {
    return {
      url: `http://pirategram.test/${uuid.v4()}.jpg`,
      description: '#probando cosas que #medio funcionan',
      likes: 0,
      liked: false,
      userId: uuid.uuid()
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
      name: 'Superman',
      username: `super_${uuid.v4()}`,
      password: uuid.uuid(),
      email: `${uuid.v4()}@fortress.com`
    }
  }
}

module.exports = fixtures
