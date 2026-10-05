"use client";

import { use } from "react";

import { PostType } from "@/app/types";

type PostsProps = { posts: Promise<PostType[]> };

export default function Posts({ posts }: PostsProps) {
  const allPosts = use(posts);

  return (
    <ul>
      {allPosts.map((post) => (
        <li key={post.id}>{post.title}</li>
      ))}
    </ul>
  );
}
