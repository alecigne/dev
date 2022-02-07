;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.4 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Intervals
;; Activity 4: UFO #3

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Constants

(define WIDTH 300)
(define HEIGHT 600)
(define CLOSE (* HEIGHT 2/3))
(define SCENE (empty-scene WIDTH HEIGHT))
(define UFO (overlay (circle 10 "solid" "green")
                     (rectangle 40 4 "solid" "green")))

;; * Data definitions

;; A UfoPosition is a Number falling into one of three intervals:
;; – between 0 and CLOSE
;; – between CLOSE and HEIGHT
;; – below HEIGHT
;; Interpretation: number of pixels between the top and the UFO.

;; * Functions

;; render: UfoPosition -> Image
;; Place the UFO at a given height into the center of the scene.
(check-expect (render 11) (place-image UFO (/ WIDTH 2) 11 SCENE))
(define (render ufo-position)
  (place-image UFO (/ WIDTH 2) ufo-position SCENE))

;; render/status: UfoPosition -> Image
;; Add a status line to the scene created by render.
(check-expect (render/status 10)
              (place-image (text "descending" 11 "green")
                           40 20
                           (render 10)))
(define (render/status ufo-position)
  (place-image
   (cond
     [(<= 0 ufo-position CLOSE)
      (text "descending" 11 "green")]
     [(and (< CLOSE ufo-position) (<= ufo-position HEIGHT))
      (text "closing in" 11 "orange")]
     [(> ufo-position HEIGHT)
      (text "landed" 11 "red")])
     40 20
     (render ufo-position)))

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
    [to-draw render/status]))
