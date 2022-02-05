;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.2 Fixed-Size Data / Functions and Programs / Computing
;; Exercise 25

#lang htdp/bsl

(require 2htdp/image)

(define (image-classify-flawed img)
  (cond
    [(>= (image-height img) (image-width img))
     "tall"]
    [(= (image-height img) (image-width img))
     "square"]
    [(<= (image-height img) (image-width img))
     "wide"]))

(define (image-classify-correct img)
  (cond
    [(> (image-height img) (image-width img))
     "tall"]
    [(= (image-height img) (image-width img))
     "square"]
    [(< (image-height img) (image-width img))
     "wide"]))

(image-classify-flawed (square 3 "solid" "red"))
(image-classify-correct (square 3 "solid" "red"))

;; Stepping helps to see the intermediate products and makes the
;; flawed comparison stand out: (>= 6 6) => tall
