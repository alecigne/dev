#lang racket                            ;; with htdp/bsl, images don't
                                        ;; appear in Emacs

(require 2htdp/universe)
(require 2htdp/image)

(define (number->square s)
  (square s "solid" "red"))

(define (reset state key-event)
  100)

(define BACKGROUND (empty-scene 100 100))
(define DOT (circle 3 "solid" "red"))

(define (main y)
  (big-bang y
    [to-draw place-dot-at]
    [on-key sub1-state]
    [stop-when zero?]))

(define (place-dot-at y)
  (place-image DOT 50 y BACKGROUND))

(define (sub1-state state key-event)
  (sub1 state))

(define (stop y ke)
  0)

(main 100)

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
