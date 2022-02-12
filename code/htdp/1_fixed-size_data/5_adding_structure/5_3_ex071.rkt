;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.5 Fixed-Size Data / Adding Structure / Computing with Structures
;; ex. 71

#lang htdp/bsl

;; Distances in terms of pixels
(define HEIGHT 200)
(define MIDDLE (quotient HEIGHT 2))
(define WIDTH  400)
(define CENTER (quotient WIDTH 2))

(define-struct game [left-player right-player ball])

(define game0 (make-game MIDDLE MIDDLE (make-posn CENTER CENTER)))

(game-ball game0)
(game-ball (make-game 100 100 (make-posn 200 200)))
(make-posn 200 200)

(posn? (game-ball game0))
(posn? (game-ball (make-game 100 100 (make-posn 200 200))))
(posn? (make-posn 200 200))
#true

(game-left-player game0)
(game-left-player (make-game 100 100 (make-posn 200 200)))
100
