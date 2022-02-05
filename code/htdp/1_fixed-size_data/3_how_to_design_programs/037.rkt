;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.3.2 Fixed-Size Data / How to Design Programs / Finger Exercises: Functions
;; Exercise 37

#lang htdp/bsl

;; string-rest: String -> String
;; Return a string without its first character.
;; Expectations:
;;   given: "h", expect: ""
;;   given: "hello", expect: "ello"
(define (string-rest str)
  (substring str 1))

(string-rest "h")
(string-rest "hello")
