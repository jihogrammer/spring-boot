package dev.jihogrammer.transaction.order;

class NotEnoughAccountException extends Exception {

    NotEnoughAccountException(final String message) {
        super(message);
    }

}
