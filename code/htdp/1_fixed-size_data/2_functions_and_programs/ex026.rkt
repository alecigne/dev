;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.2 Fixed-Size Data / Functions and Programs / Computing
;; Exercise 26

#lang htdp/bsl

(define (string-insert s i)
  (string-append (substring s 0 i)
                 "_"
                 (substring s i)))

;; This should insert "_" at the position 6: hellow_orld
(string-insert "helloworld" 6)

;; Yep! :white-check-mark:
