;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.3.2 Fixed-Size Data / How to Design Programs / Finger Exercises: Functions
;; Exercise 36

#lang htdp/bsl

(require 2htdp/image)

;; image-area: Image -> Number
;; Count the number of pixels in a given image.
;; Expectations:
;;   given: (square 10 "solid" "red"), expect: 100
;;   given: (rectangle 50 10 "solid" "red"), expect: 500
(define (image-area image)
  (* (image-width image)
     (image-height image)))

(image-area (square 10 "solid" "red"))
(image-area (rectangle 50 10 "solid" "red"))
