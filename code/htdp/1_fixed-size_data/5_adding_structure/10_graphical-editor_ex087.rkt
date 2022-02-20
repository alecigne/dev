;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.10 Fixed-Size Data / Adding Structure / A Graphical Editor
;; Graphical editor - ex. 87

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Data definitions

(define-struct editor [content cursor])
;; An Editor is a structure: (make-editor String Number)
;; Interpretation: An editor comprising of the text content and the
;; position of the cursor.
;; Examples:
;; |hello world
(define cursor-beginning (make-editor "hello world" 0))
;; hello |world
(define cursor-in (make-editor "hello world" 6))
;; hello world|
(define cursor-end (make-editor "hello world" 11))

;; * Constants

(define SCENE-WIDTH 400)
(define SCENE-HEIGHT 40)
(define SCENE (empty-scene SCENE-WIDTH SCENE-HEIGHT))
(define CURSOR (rectangle 1 SCENE-HEIGHT "solid" "red"))
(define FONT-SIZE 25)
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

;; editor-pre: Editor -> String
;; Get the part before the cursor.
(define (editor-pre editor)
  (substring (editor-content editor) 0 (editor-cursor editor)))

;; editor-post: Editor -> String
;; Get the part after the cursor.
(define (editor-post editor)
  (substring (editor-content editor) (editor-cursor editor)))

;; edit: Editor KeyEvent -> Editor
;; Add a single-character at the current cursor position, or delete
;; the character to the left of the cursor, if any, if backspace is
;; pressed. Move the cursor one character to the left or right if the
;; left or right arrow is pressed.
(define (edit editor key-event)
  (cond
    [(key=? key-event "\b") (delete editor)]
    [(or (key=? "\t" key-event) (key=? "\r" key-event)) editor]
    [(and (= (string-length key-event) 1)
          (not (will-overflow? editor key-event)))
     (insert editor key-event)]
    [(key=? "left" key-event) (cursor-left editor)]
    [(key=? "right" key-event) (cursor-right editor)]
    [else editor]))

;; delete: Editor -> Editor
;; Delete a character before the cursor.
(define (delete editor)
  (if (not (cursor-at-beginning editor))
      (make-editor (string-append
                    (string-remove-last (editor-pre editor))
                    (editor-post editor))
                   (sub1 (editor-cursor editor)))
      editor))

;; string-remove-last: String -> String
;; Remove the last character of the given string.
(define (string-remove-last str)
  (if (= 0 (string-length str))
      str
      (substring str 0 (sub1 (string-length str)))))

;; insert: Editor KeyEvent -> Editor
;; Insert a character at the current cursor position.
(define (insert editor key-event)
  (make-editor (string-append (editor-pre editor)
                              key-event
                              (editor-post editor))
               (add1 (editor-cursor editor))))

;; will-overflow?: Editor KeyEvent -> Boolean
;; Check if the next insertion will cause an overflow.
(define (will-overflow? editor key-event)
  (< (image-width SCENE)
     (image-width (render (insert editor key-event)))))

;; cursor-left: Editor -> Editor
;; Move the cursor to the left.
(define (cursor-left editor)
  (make-editor (editor-content editor)
               (if (cursor-at-beginning editor)
                   (editor-cursor editor)
                   (sub1 (editor-cursor editor)))))

;; cursor-right: Editor -> Editor
;; Move the cursor to the right.
(define (cursor-right editor)
  (make-editor (editor-content editor)
               (if (cursor-at-end editor)
                   (editor-cursor editor)
                   (add1 (editor-cursor editor)))))

;; cursor-at-end: Editor -> Boolean
;; Check if the cursor is at the end of the editor.
(define (cursor-at-end editor)
  (= (editor-cursor editor)
     (string-length (editor-content editor))))

;; cursor-at-beginning: Editor -> Boolean
;; Check if the cursor is at the end of the editor.
(define (cursor-at-beginning editor)
  (zero? (editor-cursor editor)))

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
                 CURSOR)
                SCENE))

;; ** editor-pre

(check-expect
 (editor-pre cursor-beginning)
 "")

(check-expect
 (editor-pre cursor-in)
 "hello ")

(check-expect
 (editor-pre cursor-end)
 "hello world")

;; ** editor-post

(check-expect
 (editor-post cursor-beginning)
 "hello world")

(check-expect
 (editor-post cursor-in)
 "world")

(check-expect
 (editor-post cursor-end)
 "")

;; ** edit

;; insertion

(check-expect (edit cursor-beginning "a") (make-editor "ahello world" 1))
(check-expect (edit cursor-in "a") (make-editor "hello aworld" 7))
(check-expect (edit cursor-end "a") (make-editor "hello worlda" 12))

;; deletion

(check-expect (edit cursor-in "\b") (make-editor "helloworld" 5))
(check-expect (edit cursor-beginning "\b") cursor-beginning)

;; moving

(check-expect (edit cursor-in "left") (make-editor "hello world" 5))
(check-expect (edit cursor-beginning "left") cursor-beginning)
(check-expect (edit cursor-in "right") (make-editor "hello world" 7))
(check-expect (edit cursor-end "right") cursor-end)

;; ignored keys

(check-expect (edit cursor-in "\t") cursor-in)
(check-expect (edit cursor-in "\r") cursor-in)
(check-expect (edit cursor-in "up") cursor-in)
(check-expect (edit cursor-in "down") cursor-in)

;; ** delete

(check-expect
 (delete cursor-in)
 (make-editor "helloworld" 5))

(check-expect
 (delete cursor-beginning)
 (make-editor "hello world" 0))

(check-expect
 (delete cursor-end)
 (make-editor "hello worl" 10))

;; ** insert

(check-expect
 (insert cursor-beginning "a")
 (make-editor "ahello world" 1))

(check-expect
 (insert cursor-in "a")
 (make-editor "hello aworld" 7))

(check-expect
 (insert cursor-end "a")
 (make-editor "hello worlda" 12))

;; ** string helpers

(check-expect (string-remove-last "hello") "hell")
(check-expect (string-remove-last "") "")

;; * Main

;; main: String -> Editor
;; Drive the editor program from an initial string. The cursor will be
;; placed at the end of the content.
(define (main initial-content)
  (big-bang (make-editor
             initial-content
             (string-length initial-content))
    [to-draw render]
    [on-key edit]))

(main "hello world!")

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
