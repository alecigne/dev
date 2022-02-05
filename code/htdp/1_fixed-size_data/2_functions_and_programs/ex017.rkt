;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.1 Fixed-Size Data / Functions and Programs / Functions
;; Exercise 17

#lang htdp/bsl

(require 2htdp/image)

(define (image-classify image)
  (cond
    [(> (image-height image) (image-width image))
     "tall"]
    [(= (image-height image) (image-width image))
     "square"]
    [else "wide"]))

(image-classify (square 10 "solid" "red"))
