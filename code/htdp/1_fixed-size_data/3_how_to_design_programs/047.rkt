;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.3.7 Fixed-Size Data / How to Design Programs / Virtual Pet Worlds
;; Exercise 47: gauge-prog

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Constants

;; ** Scene

(define SCENE-WIDTH 400)
(define SCENE-HEIGHT 100)
(define SCENE (empty-scene SCENE-WIDTH SCENE-HEIGHT))

;; ** Gauge

(define GAUGE-COLOR "red")
(define GAUGE-STYLE "solid")
(define GAUGE-HEIGHT (/ SCENE-HEIGHT 20))
(define GAUGE-FROM-TOP (/ SCENE-HEIGHT 10))
(define GAUGE-SPEED 1) ; pixels per clock tick
(define FRAME (rectangle 100 GAUGE-HEIGHT "outline" "black"))

;; * Data definitions

;; Happiness is a Number.
;; Interpretation: the level of happiness.

;; * Functions

;; draw-scene: Happiness -> Image
;; Draw the scene, comprising of a happiness bar.
(check-expect (draw-scene 50)
              (place-image (draw-gauge 50)
                           60 GAUGE-FROM-TOP
                           SCENE))
(define (draw-scene happiness)
  (place-image (draw-gauge happiness)
               60 GAUGE-FROM-TOP
               SCENE))

;; draw-gauge: Happiness -> Image
;; Draw a gauge from a happiness level.
(check-expect (draw-gauge 50)
              (overlay/align
               "left" "middle" FRAME
               (rectangle 50 GAUGE-HEIGHT GAUGE-STYLE GAUGE-COLOR)))
(define (draw-gauge happiness)
  (overlay/align
   "left" "middle" FRAME
   (rectangle happiness GAUGE-HEIGHT GAUGE-STYLE GAUGE-COLOR)))

;; next-happiness-level: Happiness -> Happiness
;; Return the next happiness level from current happiness.
(check-expect (next-happiness-level 50) 49.9)
(check-expect (next-happiness-level 0) 0)
(define (next-happiness-level happiness)
  (cond
    [(< (- happiness 0.1) 0) 0]
    [else (- happiness 0.1)]))

;; act-on-happiness: Happiness -> Happiness
;; Return a new happiness level following an action.
(check-expect (act-on-happiness 12 "up") 16)
(check-expect (act-on-happiness 39 "up") 52)
(check-expect (act-on-happiness 100 "up") 100)
(check-expect (act-on-happiness 0 "up") 2)
(check-expect (act-on-happiness 15 "down") 12)
(check-expect (act-on-happiness 30 "down") 24)
(define (act-on-happiness happiness key-event)
  (cond
    [(key=? key-event "up") (happier happiness)]
    [(key=? key-event "down") (sadder happiness)]
    [else happiness]))

;; happier: Happiness -> Happiness
;; Increase the level of happiness.
(check-expect (happier 12) 16)
(check-expect (happier 99) 100)
(check-expect (happier 100) 100)
(check-expect (happier 0) 2)
(define (happier happiness)
  (cond
    [(> (+ happiness (/ happiness 3)) 100) 100]
    [(zero? happiness) 2]
    [else (+ happiness (/ happiness 3))]))   ; no `let' construct in BSL

;; sadder: Happiness -> Happiness
;; Decrease the level of happiness.
(check-expect (sadder 15) 12)
(check-expect (sadder 90) 72)
(check-expect (sadder 0) 0)
(define (sadder happiness)
  (if (< (- happiness (/ happiness 5)) 0)
      0
      (- happiness (/ happiness 5))))   ; no `let' construct in BSL

;; * Main

;; main: Happiness -> Happiness
;; Drive the program starting from an initial happiness level.
(define (main initial-happiness)
  (big-bang initial-happiness
    [to-draw draw-scene]
    [on-tick next-happiness-level]
    [on-key act-on-happiness]))
