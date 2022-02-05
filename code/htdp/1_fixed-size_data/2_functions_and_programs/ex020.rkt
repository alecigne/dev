;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.1 Fixed-Size Data / Functions and Programs / Functions
;; Exercise 20

#lang htdp/bsl

(define (string-delete str i)
  (string-append
   (substring str 0 i)
   (substring str (add1 i))))

(string-delete "helllo_world" 2)
