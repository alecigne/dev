;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.1.7 Fixed-Size Data / Arithmetic / Predicates: Know Thy Data
;; Exercise 9

#lang htdp/bsl

(define in 42)

(cond
  [(string? in) (string-length in)]
  [(image? in) (* (image-width in) (image-height in))]
  [(number? in) in]
  [(not in) 20]
  [in 10])
