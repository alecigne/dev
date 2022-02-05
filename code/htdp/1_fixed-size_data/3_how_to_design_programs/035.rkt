;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.3.2 Fixed-Size Data / How to Design Programs / Finger Exercises: Functions
;; Exercise 35

#lang htdp/bsl

;; string-last: String -> 1String
;; Extract the last character from a non-empty string.
;; Expectations:
;;   given: "h", expect: "h"
;;   given: "hello", expect: "o"
(define (string-last str)
  (string-ith str (sub1 (string-length str))))

(string-last "h")
(string-last "hello")
