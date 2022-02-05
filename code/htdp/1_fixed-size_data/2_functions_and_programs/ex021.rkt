;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.2 Fixed-Size Data / Functions and Programs / Computing
;; Exercise 21

#lang htdp/bsl

(define (ff a)
  (* 10 a))

(ff (ff 1))
(+ (ff 1) (ff 1))
