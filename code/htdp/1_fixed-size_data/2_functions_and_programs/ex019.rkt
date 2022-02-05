;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.1 Fixed-Size Data / Functions and Programs / Functions
;; Exercise 19

#lang htdp/bsl

(define (string-insert str i)
  (string-append
   (substring str 0 i)
   "_"
   (substring str i)))

(string-insert "helloworld" 5)
