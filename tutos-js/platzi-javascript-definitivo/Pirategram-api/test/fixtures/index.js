export default {
  getImage () {
    return {
      id: 'adcbc806-f6bf-40c3-97ce-223392b8af38',
      publicId: '718etXJyei5BQt4xjs98px',
      userId: 'superman',
      src: 'http://pirategram.test/718etXJyei5BQt4xjs98px.jpg',
      liked: false,
      likes: 0,
      description: '#superhero',
      tags: [ 'superhero' ],
      createdAt: new Date().toString()
    }
  },

  getImages () {
    return [
      this.getImage(),
      this.getImage(),
      this.getImage()
    ]
  },

  getImagesByTag () {
    return [
      this.getImage(),
      this.getImage()
    ]
  },

  getUser () {
    return {
      id: '1cd34a87-9343-4e36-8a5a-84d563001f12',
      name: 'Superman',
      username: 'Kal-El',
      email: 'superman@krypton.com',
      password: 'KalEl',
      createdAt: new Date().toString()
    }
  }
}
