;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.2 Fixed-Size Data / Functions and Programs / Computing
;; Exercise 22

#lang htdp/bsl

(define (distance-to-origin x y)
  (sqrt (+ (sqr x) (sqr y))))

(distance-to-origin 3 4)
