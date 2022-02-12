;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.9 Fixed-Size Data / Adding Structure / Structure in the World
;; Space game v1

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Data definitions

(define-struct space-game [ufo tank])
;; A SpaceGame is a structure:
;; (make-space-game Number Number)
;; Interpretation: a space game, with two positions for a tank and a
;; ufo. The two positions are, respectively, distances from the top of
;; the scene and the left of the scene.
;; Examples:
(define space-game1 (make-space-game 10 10))
(define space-game2 (make-space-game 0 50))

;; * Constants

(define SCENE-WIDTH 800)
(define SCENE-HEIGHT 500)
(define SCENE
  (place-image/align (bitmap "files/sky.jpeg")
                     0 0 "left" "top"
                     (empty-scene SCENE-WIDTH SCENE-HEIGHT)))

(define UFO (bitmap "files/ufo.png"))
(define UFO-X-POS (/ SCENE-WIDTH 2))
(define TANK (bitmap "files/tank.png"))
(define TANK-Y-POS (* SCENE-HEIGHT 3/4))

;; * Functions

;; draw-scene: SpaceGame -> Image
;; Draw a space game scene.
(define (draw-scene space-game)
  (place-image UFO
               UFO-X-POS (space-game-ufo space-game)
               (place-image
                TANK
                (space-game-tank space-game) TANK-Y-POS
                SCENE)))

;; next-position: SpaceGame -> SpaceGame
;; Compute the next position of the space game.
(define (next-position space-game)
  (make-space-game (add1 (space-game-ufo space-game))
                   (add1 (space-game-tank space-game))))

;; * Tests

;; draw-scene

(check-expect
 (draw-scene space-game1)
 (place-image UFO
              UFO-X-POS (space-game-ufo space-game1)
              (place-image
               TANK
               (space-game-tank space-game1) TANK-Y-POS
               SCENE)))

;; next-position

(check-expect
 (next-position space-game1)
 (make-space-game (add1 (space-game-ufo space-game1))
                  (add1 (space-game-tank space-game1))))

;; * Main

;; animate-space-game: SpaceGame -> SpaceGame
;; Drive the space game from an initial configuration.
(define (animate-space-game initial-configuration)
  (big-bang initial-configuration
    [to-draw draw-scene]
    [on-tick next-position]))

(animate-space-game
 (make-space-game 0 0))

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
