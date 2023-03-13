package com.KoreaIT.java.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
	static List<Post> posts = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("==프로그램 시작==");
		int lastPostId = 3;
		makeTestData();
		
		while(true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();
			
			if(command.equals("exit")) {
				break;
			}
			
			if(command.length()==0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			else if(command.equals("post write")) {
				int id = lastPostId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				String regDate = Util.getNowDateStr();
				String updateDate = "";
				
				Post post = new Post(id, title, body, regDate, updateDate);
				posts.add(post);
				lastPostId++;
				System.out.printf("%d번 게시글이 생성되었습니다. \n", id);
			}
			else if(command.equals("post list")) {
				if(posts.size()==0) {
					System.out.println("게시글이 존재하지 않습니다.");
				}
				else {
					System.out.println("  번호 | 제목");
					for(int i = posts.size()-1; i >= 0; i--) {
						Post post = posts.get(i);
						System.out.printf("   %d  | %s \n", post.id, post.title);
					}
				}
			}
			else if(command.startsWith("post detail")) {
				String[] commandBits = command.split(" ");
				
				if(commandBits.length < 3 || commandBits.length > 3) {
					System.out.println("post detail (숫자)를 입력해주세요.");
					continue;
				}
				int id = Integer.parseInt(commandBits[2]);
				
				Post foundpost = null;
				for(int i = 0; i < posts.size(); i++) {
					Post post = posts.get(i);
					if(post.id == id) {
						foundpost = post;
						break;
					}
				}
				
				if(foundpost==null) {
					System.out.printf("%d번 게시글이 존재하지 않습니다. \n", id);
				}
				else {
					System.out.printf("번호 : %d \n", foundpost.id);
					System.out.printf("날짜 : %s \n", foundpost.regDate);
					System.out.printf("제목 : %s \n", foundpost.title);
					System.out.printf("내용 : %s \n", foundpost.body);
				}
			}
			else if(command.startsWith("post modify")) {
				String[] commandBits = command.split(" ");
				
				if(commandBits.length < 3 || commandBits.length > 3) {
					System.out.println("post modify (숫자)를 입력해주세요.");
					continue;
				}
				int id = Integer.parseInt(commandBits[2]);
				
				Post foundpost = null;
				for(int i = 0; i < posts.size(); i++) {
					Post post = posts.get(i);
					if(post.id == id) {
						foundpost = post;
						break;
					}
				}
				
				if(foundpost==null) {
					System.out.printf("%d번 게시글이 존재하지 않습니다. \n", id);
				}
				else {
					System.out.printf("제목 : ");
					String title = sc.nextLine();
					System.out.printf("내용 : ");
					String body = sc.nextLine();
					String updateDate = Util.getNowDateStr();
					
					foundpost.title = title;
					foundpost.body = body;
					foundpost.updateDate = updateDate;
					System.out.printf("%d번 게시글을 수정했습니다. \n", id);
				}
			}
			else if(command.startsWith("post delete")) {
				String[] commandBits = command.split(" ");
				
				if(commandBits.length < 3 || commandBits.length > 3) {
					System.out.println("post delete (숫자)를 입력해주세요.");
					continue;
				}
				int id = Integer.parseInt(commandBits[2]);
				
				int foundIndex = -1;
				for(int i = 0; i < posts.size(); i++) {
					Post post = posts.get(i);
					if(post.id == id) {
						foundIndex = i;
						break;
					}
				}
				
				if(foundIndex==-1) {
					System.out.printf("%d번 게시글이 존재하지 않습니다. \n", id);
				}
				else {
					posts.remove(foundIndex);
					System.out.printf("%d번 게시글을 삭제했습니다.\n", id);
				}
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		System.out.println("==프로그램 종료==");
	}
	private static void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");
		posts.add(new Post(1, "제목1", "내용1", Util.getNowDateStr(), ""));
		posts.add(new Post(2, "제목2", "내용2", Util.getNowDateStr(), ""));
		posts.add(new Post(3, "제목3", "내용3", Util.getNowDateStr(), ""));
	}
}

class Post {
	int id;
	String title;
	String body;
	String regDate;
	String updateDate;
	
	Post(int id, String title, String body, String regDate, String updateDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}
}
