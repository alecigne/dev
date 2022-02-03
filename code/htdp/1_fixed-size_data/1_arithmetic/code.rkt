;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.1 Fixed-Size Data / Arithmetic
;; Exercises 1-10

#lang htdp/bsl

(require 2htdp/image)

;; * Exercise 1

(define x 3)
(define y 4)

(sqrt (+ (sqr x)
         (sqr y)))

;; * Exercise 2

(define prefix "hello")
(define suffix "world")

(string-append prefix "_" suffix)

;; * Exercise 3

(define str "helloworld")
(define i 5)

(string-append
 (substring str 0 i)
 "_"
 (substring str i))

;; * Exercise 4

(string-append
 (substring str 0 i)
 (substring str (add1 i)))              ; i should be < 10

;; * Exercise 5

;; A car

(define car-unit 10)
(define wheel (circle (* 4 car-unit) "solid" "black"))

(define glass (rectangle (* 12 car-unit)
                         (* 5 car-unit)
                         "solid" "lightblue"))

(define body (rectangle (* 25 car-unit)
                        (* 7 car-unit)
                        "solid" "red"))

(overlay/xy
 glass (* -6 car-unit) (* 5 car-unit)
 (overlay/xy
  body 0 (* 3 car-unit)
  (overlay/xy wheel (* 17 car-unit) 0 wheel)))

;; A tree

(define tree-unit 5)

(overlay/xy
 (circle (* 10 tree-unit) "solid" "green")
 (* 7 tree-unit) (* 18 tree-unit)
 (rectangle (* 6 tree-unit) (* 10 tree-unit) "solid" "brown"))

;; * Exercise 6

(define cat (bitmap "files/cat.png"))

(* (image-width cat)
   (image-height cat))

;; * Exercise 7

(define sunny #true)
(define friday #false)

(or (not sunny)
    friday)

;; * Exercise 8

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

;; * Exercise 9

(define in 42)

(cond
  [(string? in) (string-length in)]
  [(image? in) (* (image-width in) (image-height in))]
  [(number? in) in]
  [(not in) 20]
  [in 10])

;; * Exercise 10

;; Done :)
