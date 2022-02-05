;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.2 Fixed-Size Data / Functions and Programs / Computing
;; Exercise 24

#lang htdp/bsl

(define (==> x y)
  (or (not x) y))

(==> #true #false)

;; (==> #true #false)
;; (or (not #true) #false)
;; (or #false #false)
;; #false
