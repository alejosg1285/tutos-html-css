'use strict'

const Client = require('./lib/client')

export.createClient = function (options) {
  return new Client(options)
}
