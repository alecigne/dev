;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.1 Fixed-Size Data / Functions and Programs / Functions
;; Exercise 11

#lang htdp/bsl

(define (distance x y)
  (sqrt (+ (sqr x)
           (sqr y))))

(distance 3 4)
(distance 12 5)
