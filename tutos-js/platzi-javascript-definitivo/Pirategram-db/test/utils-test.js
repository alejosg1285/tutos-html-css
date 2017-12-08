'use strict'

const test = require('ava')
const utils = require('../lib/utils')

test('extracting hashtags from text', t => {
  let tags = utils.extractTags('a #cat is #AweSomE #alWays #100')

  t.deepEqual(tags, [
    'cat',
    'awesome',
    'always',
    '100'
  ])

  tags = utils.extractTags('the cats are the animals')
  t.deepEqual(tags, [])

  tags = utils.extractTags()
  t.deepEqual(tags, [])

  tags = utils.extractTags(null)
  t.deepEqual(tags, [])

  t.pass()
})

test('encrypt password', t => {
  let password = 'foo123'
  let encrypted = '02b353bf5358995bc7d193ed1ce9c2eaec2b694b21d2f96232c9d6a0832121d1'

  let result = utils.encrypt(password)
  t.is(result, encrypted)
})
