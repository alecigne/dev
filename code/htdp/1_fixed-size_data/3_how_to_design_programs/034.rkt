;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.3.2 Fixed-Size Data / How to Design Programs / Finger Exercises: Functions
;; Exercise 34

#lang htdp/bsl

;; string-first: String -> 1String
;; Extract the first character from a non-empty string.
;; Expectations:
;;   given: "h", expect: "h"
;;   given: "hello", expect: "h"
(define (string-first str)
  (string-ith str 0))

(string-first "h")
(string-first "hello")

;; 1. Express how you wish to represent information as data.

;; No need to do it as we work on strings directly.

;; 2. Write down a signature, a statement of purpose, and a function header.

;; string-first: String -> 1String
;; Extracts the first character from a non-empty string.
;; (define (string-first str)
;;   "")

;; 3. Illustrate the signature and the purpose statement with some functional
;; examples. To construct a functional example, pick one piece of data from each
;; input class from the signature and determine what you expect back.

;; string-first: String -> 1String
;; Extracts the first character from a non-empty string.
;; given: "h", expect: "h"
;; given: "hello", expect: "h"
;; (define (string-first str)
;;   "")

;; 4. Take inventory.

;; string-first: String -> 1String
;; Extracts the first character from a non-empty string.
;; given: "h", expect: "h"
;; given: "hello", expect: "h"
;; (define (string-first str)
;;   ... str ...)

;; 5. Code.

;; string-first: String -> 1String
;; Extracts the first character from a non-empty string.
;; given: "h", expect: "h"
;; given: "hello", expect: "h"
;; (define (string-first str)
;;   (string-ith str 0))

;; 6. Test the function on the examples.

;; string-first: String -> 1String
;; Extracts the first character from a non-empty string.
;; given: "h", expect: "h"
;; given: "hello", expect: "h"
;; (define (string-first str)
;;   (string-ith str 0))
;;
;; (string-first "h")
;; (string-first "hello")
