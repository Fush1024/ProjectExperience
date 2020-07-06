package com.quickly.devploment.current.publish;

import lombok.extern.slf4j.Slf4j;

/**
 * 逃逸
 *
 */
@Slf4j
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape () {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
