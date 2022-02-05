;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.1 Fixed-Size Data / Functions and Programs / Functions
;; Exercise 16

#lang htdp/bsl

(require 2htdp/image)

(define cat (bitmap "files/cat.png"))

(define (image-area image)
  (* (image-width image)
     (image-height image)))

(image-area cat)
