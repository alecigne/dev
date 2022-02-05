;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.1 Fixed-Size Data / Functions and Programs / Functions
;; Exercise 14

#lang htdp/bsl

(define (string-empty? str)
  (= 0 (string-length str)))

(define (string-last str)
  (if (string-empty? str)
      ""
      (string-ith str (sub1 (string-length str)))))
