package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex2.entity.Memo;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample " + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {
        //데이터베이스에 존재하는 mno
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("==========================");

        if(result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
     }
    @Test
    @Transactional
    public void testSelect2() {
        //데이터베이스에 존재하는 mno
        Long mno = 100L;

        Memo memo = memoRepository.getOne(mno);

        System.out.println("==========================");

            System.out.println(memo);
     }

     @Test
    public void testUpdate() {
         Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
         Memo saved = memoRepository.save(memo);
         System.out.println("saved = " + saved);
    }

    @Test
    public void testdelete() {

        Long mno = 100L;

        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault() {
        //1페이지 10개
        Pageable pageable = PageRequest.of(0, 10);

        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println("result = " + result);

        System.out.println("=================================");

        System.out.println("total Pages: "+ result.getTotalPages());
        System.out.println("Total Count: " + result.getTotalElements());
        System.out.println("Page Number: " + result.getNumber());
        System.out.println("Page Size: " + result.getSize());
        System.out.println("has Nex PAge?: " + result.hasNext());
        System.out.println("first page?: " + result.isFirst());
    }


}