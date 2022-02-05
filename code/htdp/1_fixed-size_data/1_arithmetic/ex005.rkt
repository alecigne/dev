;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.1.4 Fixed-Size Data / Arithmetic / The Arithmetic of Images
;; Exercise 5

#lang racket                            ; with htdp/bsl, images don't
                                        ; appear in Emacs

(require 2htdp/image)

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
