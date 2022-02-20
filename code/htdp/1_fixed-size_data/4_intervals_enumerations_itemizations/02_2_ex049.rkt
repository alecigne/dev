;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.2 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Computing Conditionally
;; Exercise 49

#lang racket                            ; images don't show up in Emacs with
                                        ; htdp/bsl

(require 2htdp/image)

;; * First part

;; ** y = 100

(- 200 (cond [(> 100 200) 0] [else 100]))
(- 200 (cond [#false 0] [else 100]))
(- 200 (cond [else 100]))
(- 200 100)
100

;; ** y = 210

(- 200 (cond [(> 210 200) 0] [else 210]))
(- 200 (cond [#true 0] [else 210]))
(- 200 0)
200

;; * Second part

(define WIDTH 100)
(define HEIGHT 60)
(define MTSCN (empty-scene WIDTH HEIGHT))
(define ROCKET (bitmap "files/rocket.png"))
(define ROCKET-CENTER-TO-TOP
  (- HEIGHT (/ (image-height ROCKET) 2)))

(define (create-rocket-scene.v5 h)
  (place-image ROCKET 50
               (cond
                 [(<= h ROCKET-CENTER-TO-TOP) h]
                 [(> h ROCKET-CENTER-TO-TOP) ROCKET-CENTER-TO-TOP])
               MTSCN))
