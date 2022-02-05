;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.1.6 Fixed-Size Data / Arithmetic / Mixing It Up with Booleans
;; Exercise 8

#lang htdp/bsl

(require 2htdp/image)

(define cat (bitmap "files/cat.png"))

(if (>= (image-height cat) (image-width cat))
    "tall"
    "wide")

(define IMAGE (rectangle 50 50 "solid" "red"))
(define IMAGE-HEIGHT (image-height IMAGE))
(define IMAGE-WIDTH (image-width IMAGE))

(if (> IMAGE-HEIGHT IMAGE-WIDTH)
    "tall"
    (if (= IMAGE-HEIGHT IMAGE-WIDTH)
        "square"
        "wide"))
