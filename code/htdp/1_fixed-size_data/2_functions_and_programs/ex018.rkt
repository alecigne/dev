;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.1 Fixed-Size Data / Functions and Programs / Functions
;; Exercise 18

#lang htdp/bsl

(define (string-join str1 str2)
  (string-append str1 "_" str2))

(string-join "hello" "world")
