;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.7 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Finite State Worlds
;; Door Simulation - ex. 062

;; During a door simulation the “open” state is barely visible. Modify
;; door-simulation so that the clock ticks once every three seconds. Rerun the
;; simulation.

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Data definitions

(define LOCKED "locked")
(define CLOSED "closed")
(define OPEN "open")

;; A Door is one of:
;; - LOCKED
;; - CLOSED
;; - OPEN
;; Interpretation: a door in a given state.

;; * Constants

(define FONT-SIZE 40)

;; * Functions

;; door-render: Door -> Image
;; Draw a door in an appropriate state.
(define (door-render door)
  (cond
    [(equal? door LOCKED) (text LOCKED FONT-SIZE "red")]
    [(equal? door CLOSED) (text CLOSED FONT-SIZE "orange")]
    [(equal? door OPEN) (text OPEN FONT-SIZE "green")]))

;; door-closer: Door -> Door
;; Close the door automatically from a given state.
(define (door-closer door)
  (cond
    [(equal? door LOCKED) LOCKED]
    [(equal? door CLOSED) CLOSED]
    [(equal? door OPEN) CLOSED]))

;; door-actions: Door KeyEvent -> Door
;; Apply an action to the door.
(define (door-actions door key-event)
  (cond
    [(and (equal? door LOCKED)
          (string=? key-event "u"))
     CLOSED]
    [(and (equal? door CLOSED)
          (string=? key-event " "))
     OPEN]
    [(and (equal? door CLOSED)
          (string=? key-event "l"))
     LOCKED]
    [(and (equal? door OPEN)
          (string=? key-event "c"))
     CLOSED]
    [else door]))

;; * Main

;; door-simulation: Door -> Door
;; Drive the door simulation from an initial state.
(define (door-simulation door)
  (big-bang door
    [to-draw door-render]
    [on-tick door-closer 10]
    [on-key door-actions]))

;; * Tests

;; door-render

(check-expect (door-render LOCKED) (text LOCKED FONT-SIZE "red"))
(check-expect (door-render CLOSED) (text CLOSED FONT-SIZE "orange"))
(check-expect (door-render OPEN) (text OPEN FONT-SIZE "green"))

;; door-closer

(check-expect (door-closer LOCKED) LOCKED)
(check-expect (door-closer CLOSED) CLOSED)
(check-expect (door-closer OPEN) CLOSED)

;; door-actions

(check-expect (door-actions LOCKED "u") CLOSED)
(check-expect (door-actions CLOSED " ") OPEN)
(check-expect (door-actions CLOSED "l") LOCKED)
(check-expect (door-actions OPEN "c") CLOSED)
(check-expect (door-actions OPEN "a") OPEN)
(check-expect (door-actions CLOSED "a") CLOSED)

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
