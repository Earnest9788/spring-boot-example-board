package com.example.board.utils;

import org.springframework.stereotype.Component;

@Component
public class Pager {

    private int totalCount; // 페이징 적용할 전체 데이터 갯수
    private int pageNum; // 현재 페이지 번호
    private int contentNum; // 한페이지에 몇 개의 컨텐츠를 표시할지
    private int startPage = 1;
    private int endPage = 5;
    private boolean prev;
    private boolean next;
    private int currentBlock;
    private int lastBlock;

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getContentNum() {
        return this.contentNum;
    }

    public void setContentNum(int contentNum) {
        this.contentNum = contentNum;
    }

    public int getStartPage() {
        return this.startPage;
    }

    public void setStartPage(int currentBlock) {
        this.startPage = (currentBlock * 10) - 9;
    }

    public int getEndPage() {
        return this.endPage;
    }

    public void setEndPage(int getLastBlock, int getCurrentBlock) {
        
        if (getLastBlock == getCurrentBlock) {
            this.endPage = calcPage(getTotalCount(), getContentNum());
        } else {
            this.endPage = getStartPage() + 9;
        }

    }

    public boolean isPrev() {
        return this.prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return this.next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getCurrentBlock() {
        return this.currentBlock;
    }

    public void setCurrentBlock(int pageNum) {
        
        this.currentBlock = pageNum / 10;

        if (pageNum % 10 > 0) {
            this.currentBlock++;
        }

    }

    public int getLastBlock() {
        return this.lastBlock;
    }

    public void setLastBlock(int lastBlock) {
        
        this.lastBlock = totalCount / (10 * this.contentNum);

        if (totalCount % (10 * this.contentNum) > 0) {
            this.lastBlock++;
        }

    }

    public void prevnext(int pageNum) {

        if(calcPage(totalCount, contentNum) < 11){
            setPrev(false);
            setNext(false);

        } else if(pageNum > 0 && pageNum < 11){
            setPrev(false);
            setNext(true);

        } else if(getLastBlock() == getCurrentBlock()) {
            setPrev(true);
            setNext(false);
            
        } else{
            setPrev(true);
            setNext(true);
        }

    }

    public int calcPage(int totalCount, int contentNum) {

        int totalPage = totalCount / contentNum;

        if (totalCount % contentNum > 0) {
            totalPage++;
        }

        return totalPage;

    }

}
