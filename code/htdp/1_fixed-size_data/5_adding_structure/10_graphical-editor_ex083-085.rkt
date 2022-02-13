;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.10 Fixed-Size Data / Adding Structure / A Graphical Editor
;; Graphical editor - ex. 83-85

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Data definitions

(define-struct editor [pre post])
;; An Editor is a structure: (make-editor String String)
;; Interpretation: An editor whose visible text is (string-append s t)
;; with the cursor displayed between s and t.
;; Examples:
;; |hello world
(define cursor-beginning (make-editor "" "hello world"))
;; hello |world
(define cursor-in (make-editor "hello " "world"))
;; hello world|
(define cursor-end (make-editor "hello world" ""))

;; * Constants

(define SCENE (empty-scene 400 40))
(define CURSOR (rectangle 1 40 "solid" "red"))
(define FONT-SIZE 22)
(define FONT-COLOR "black")

;; * Functions

;; render: Editor -> Image
;; Display the editor.
(define (render editor)
  (overlay/align "left" "center"
                 (beside
                  (text (editor-pre editor) FONT-SIZE FONT-COLOR)
                  CURSOR
                  (text (editor-post editor) FONT-SIZE FONT-COLOR))
                 SCENE))

;; edit: Editor KeyEvent -> Editor
;; Add a single-character to the end of the pre field of the editor,
;; or delete the character to the left of the cursor, if any, if
;; backspace is pressed. Move the cursor one character to the left or
;; right if the left or right arrow is pressed.
(define (edit editor key-event)
  (cond
    [(key=? "\b" key-event) (delete editor)]
    [(or (key=? "\t" key-event) (key=? "\r" key-event)) editor]
    [(= (string-length key-event) 1) (insert editor key-event)]
    [(key=? "left" key-event) (cursor-left editor)]
    [(key=? "right" key-event) (cursor-right editor)]
    [else editor]))

;; insert: Editor KeyEvent -> Editor
;; Add a single-character to the end of the pre field of the editor.
(define (insert editor key-event)
  (make-editor (string-append (editor-pre editor) key-event)
               (editor-post editor)))

;; delete: Editor KeyEvent -> Editor
;; Delete the character immediately to the left of the cursor, if any.
(define (delete editor)
  (make-editor (string-remove-last (editor-pre editor))
               (editor-post editor)))

;; cursor-left: Editor -> Editor
;; Move the cursor to the left.
(define (cursor-left editor)
  (make-editor
   (string-remove-last (editor-pre editor))
   (string-append (string-last (editor-pre editor))
                  (editor-post editor))))

;; cursor-right: Editor -> Editor
;; Move the cursor to the right.
(define (cursor-right editor)
  (make-editor
   (string-append (editor-pre editor) (string-first (editor-post editor)))
   (string-rest (editor-post editor))))

;; string-remove-last: String -> String
;; Remove the last character of the given string.
(define (string-remove-last str)
  (if (= 0 (string-length str))
      str
      (substring str 0 (sub1 (string-length str)))))

;; string-first: String -> 1String
;; Extract the first character from a non-empty string.
(define (string-first str)
  (if (string-empty? str)
      str
      (string-ith str 0)))

;; string-rest: String -> String
;; Return a string without its first character.
(define (string-rest str)
  (if (string-empty? str)
      str
      (substring str 1)))

;; string-last: String -> 1String
;; Extract the last character from a non-empty string.
(define (string-last str)
  (if (string-empty? str)
      str
      (string-ith str (sub1 (string-length str)))))

;; string-empty?: String -> Boolean
;; Check if a string is empty.
(define (string-empty? str)
  (zero? (string-length str)))

;; * Tests

;; ** render

(check-expect
 (render cursor-beginning)
 (overlay/align "left" "center"
                (beside
                 CURSOR
                 (text "hello world" FONT-SIZE FONT-COLOR))
                SCENE))

(check-expect
 (render cursor-in)
 (overlay/align "left" "center"
                (beside
                 (text "hello " FONT-SIZE FONT-COLOR)
                 CURSOR
                 (text "world" FONT-SIZE FONT-COLOR))
                SCENE))

(check-expect
 (render cursor-end)
 (overlay/align "left" "center"
                (beside
                 (text "hello world" FONT-SIZE FONT-COLOR)
                 CURSOR
                 (text "" FONT-SIZE FONT-COLOR))
                SCENE))

;; ** edit

;; insertion

(check-expect (edit cursor-in "a") (make-editor "hello a" "world"))
(check-expect (edit cursor-end "a") (make-editor "hello worlda" ""))
(check-expect (edit cursor-beginning "a") (make-editor "a" "hello world"))

;; deletion

(check-expect (edit cursor-in "\b") (make-editor "hello" "world"))
(check-expect (edit cursor-beginning "\b") cursor-beginning)

;; moving

(check-expect (edit cursor-in "left") (make-editor "hello" " world"))
(check-expect (edit cursor-beginning "left") cursor-beginning)
(check-expect (edit cursor-in "right") (make-editor "hello w" "orld"))
(check-expect (edit cursor-end "right") cursor-end)

;; ignored keys

(check-expect (edit cursor-in "\t") cursor-in)
(check-expect (edit cursor-in "\r") cursor-in)
(check-expect (edit cursor-in "up") cursor-in)
(check-expect (edit cursor-in "down") cursor-in)

;; helpers

(check-expect (string-remove-last "hello") "hell")
(check-expect (string-remove-last "") "")
(check-expect (string-first "hi") "h")
(check-expect (string-first "") "")
(check-expect (string-rest "hello") "ello")
(check-expect (string-rest "") "")
(check-expect (string-last "hello") "o")
(check-expect (string-last "") "")
(check-expect (string-empty? "racket") #false)
(check-expect (string-empty? "") #true)

;; * Main

;; main: String -> Editor
;; Drive the editor program from an initial string.
(define (main initial-string)
  (big-bang (make-editor initial-string "")
    [to-draw render]
    [on-key edit]))

(main "hello")

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
