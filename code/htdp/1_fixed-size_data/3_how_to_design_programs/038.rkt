;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.3.2 Fixed-Size Data / How to Design Programs / Finger Exercises: Functions
;; Exercise 38

#lang htdp/bsl

;; string-remove-last: String -> String
;; Return a string without its last character.
;; given: "h", expect: ""
;; given: "funk", expect: "fun"
(define (string-remove-last str)
  (substring str 0 (sub1 (string-length str))))

(string-remove-last "h")
(string-remove-last "funk")
