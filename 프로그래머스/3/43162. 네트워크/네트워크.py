from collections import deque

def solution(n, computers):
    answer = 0
    que = deque()
    
    v = [False] * n
    for i in range(n):
        if v[i]: 
            continue
        
        v[i] = True
        answer += 1
        que.append(i)
        
        while que:
            now = que.popleft()
            for j in range(n):
                if now==j: 
                    continue
                if v[j] or computers[now][j]==0: 
                    continue
                
                v[j] = True
                que.append(j)
                
    
    
    return answer