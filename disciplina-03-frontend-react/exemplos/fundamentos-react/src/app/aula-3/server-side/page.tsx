import { PostType } from "@/app/types";

export default async function Page() {
  const response = await fetch("https://api.vercel.app/blog");

  const posts: PostType[] = await response.json();

  return (
    <ul>
      {posts.map((post) => (
        <li key={post.id}>{post.title}</li>
      ))}
    </ul>
  );
}
