package com.pyae.transaction.event;

public record TransferLogErrorEvent(int logId, Exception e) {

}
