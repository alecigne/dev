;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.4 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Intervals
;; Activity 1: UFO

;; "Stop! Study the definitions and replace the dots before you read
;; on."

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Constants

(define WIDTH 300)
(define HEIGHT 100)
(define CLOSE (/ HEIGHT 3))
(define SCENE (empty-scene WIDTH HEIGHT))
(define UFO (overlay (circle 10 "solid" "green")
                     (rectangle 40 4 "solid" "green")))

;; * Data definitions

;; A UfoPosition is a Number.
;; Interpretation: number of pixels between the top and the UFO.

;; * Functions

;; render: UfoPosition -> Image
;; Place the UFO at a given height into the center of the scene.
(check-expect (render 11) (place-image UFO (/ WIDTH 2) 11 SCENE))
(define (render ufo-position)
  (place-image UFO (/ WIDTH 2) ufo-position SCENE))

;; next: UfoPosition -> UfoPosition
;; Compute the next location of the UFO.
(check-expect (next 11) 14)
(define (next ufo-position)
  (+ ufo-position 3))

;; * Main

;; UfoPosition -> UfoPosition
;; main: Drive the program from an initial UFO position.
(define (main initial-ufo-position)
  (big-bang initial-ufo-position
     [on-tick next]
     [to-draw render]))
