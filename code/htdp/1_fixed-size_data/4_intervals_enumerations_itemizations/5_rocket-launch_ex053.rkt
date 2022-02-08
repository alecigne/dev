;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.5 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Itemizations
;; Rocket Launch - ex. 053

#lang htdp/bsl

;; A RocketLaunch is one of:
;; – "resting"
;; – NonNegativeNumber
;; Interpretation:
;; - "resting" represents a grounded rocket
;; - A number denotes the distance between the top of the canvas and
;; the bottom of the rocket.

;; Height of the scene
(define HEIGHT 300)

;; The rocket has just disappeared from the screen.
(define rocket-launch1 0)

;; The rocket is resting at the bottom of the screen.
(define rocket-launch2 HEIGHT)

;; The rocket is half-way up
(define rocket-launch3 (/ HEIGHT 2))
