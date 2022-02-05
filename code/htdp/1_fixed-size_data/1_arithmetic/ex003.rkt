;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.1.3 Fixed-Size Data / Arithmetic / Mixing It Up
;; Exercise 3

#lang htdp/bsl

(define str "helloworld")
(define i 5)

(string-append
 (substring str 0 i)
 "_"
 (substring str i))
